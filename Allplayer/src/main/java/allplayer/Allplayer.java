package allplayer;


import java.util.LinkedHashMap;


import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityExplosionPrimeEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerEatFoodEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.potion.Effect;
import cn.nukkit.scheduler.PluginTask;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class Allplayer extends PluginBase implements Listener{

	public static boolean AllPayerStop = false;
	public static boolean Movement = false;
	public static boolean BlockBreak = false;
	public static boolean BlockPlace = false;
	public static boolean Chat = false;
	public static boolean Pvp = false;
	public static boolean Food = false;
	public static boolean Explode = false;
	private LinkedHashMap<String, Object> config;

	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(pd.getName()+" version"+pd.getVersion()+" is enabled");
		this.getServer().getScheduler().scheduleRepeatingTask(new SaveData(this), 20);
		getDataFolder().mkdir();
		this.config = (LinkedHashMap<String, Object>) (new Config(getDataFolder() + "/config.yml", Config.YAML, 
				new LinkedHashMap<String, Object>() { 
			{ 
				put("allstop", false); 
				put("movement", false); 
				put("break", false); 
				put("place", false); 
				put("chat", false); 
				put("food", false); 
				put("explode", false); 
				put("pvp", false); 
			} 
		})).getAll();


		AllPayerStop = (Boolean) config.get("allstop");
		Movement = (Boolean) config.get("movement");
		BlockBreak = (Boolean) config.get("break");
		BlockPlace = (Boolean) config.get("place");
		Chat = (Boolean) config.get("chat");
		Food = (Boolean) config.get("food");
		Explode = (Boolean) config.get("explode");
		Pvp = (Boolean) config.get("pvp");
		this.save();
	}

	@Override
	public void onDisable() {
		PluginDescription pd = getDescription();
		this.getLogger().info(pd.getName()+" version"+pd.getVersion()+" is Disabled");
		this.save();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if(command.getName().equalsIgnoreCase("allplayer")){
			if(player.hasPermission("allplayer")){
				if(args.length==0){
					player.sendMessage(this.help());
					//명령어 설명
				}
			}
		}
		if(command.getName().equalsIgnoreCase("allplace")){
			if(player.hasPermission("allplace.commands"))
			{
				if(BlockPlace==true){
					BlockPlace = false;
					this.config.put("place", Boolean.valueOf(false));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Allow Placing the block!");
					return true;
				}else{	 //off
					BlockPlace = true;
					this.config.put("place", Boolean.valueOf(true));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Not Allow Placing the block!");
					return true;
				}
			}
			player.sendMessage(TextFormat.RED+"[Allplayer]+"+TextFormat.GREEN+" You don't have permission to use this commnads");
		}

		if(command.getName().equalsIgnoreCase("allbreak")){
			if(player.hasPermission("allbreak.commands")){
				if(BlockBreak==true){
					BlockBreak = false;
					this.config.put("break", Boolean.valueOf(false));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Allow Breaking the block!");
					return true;
				}else{	 //off
					BlockBreak = true;
					this.config.put("break", Boolean.valueOf(true));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Not Allow Breaking the block!");
					return true;
				}
			}
			player.sendMessage(TextFormat.RED+"[Allplayer]+"+TextFormat.GREEN+" You don't have permission to use this commnads");
		}

		if(command.getName().equalsIgnoreCase("allchat")){
			if(player.hasPermission("allchat.commands")){
				if(Chat==true){
					Chat = false;
					this.config.put("chat", Boolean.valueOf(false));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Allow the Chat!");
					return true;
				}else{
					Chat = true;
					this.config.put("chat", Boolean.valueOf(true));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Not Allow the Chat!");
					return true;
				}
			}
			player.sendMessage(TextFormat.RED+"[Allplayer]+"+TextFormat.GREEN+" You don't have permission to use this commnads");
		}

		if(command.getName().equalsIgnoreCase("allmove")){
			if(player.hasPermission("allmove.commands")){
				if(Movement==true){
					Movement = false;
					this.config.put("movement", Boolean.valueOf(false));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Allow the movement!");
					return true;
				}else{
					Movement = true;
					this.config.put("movement", Boolean.valueOf(true));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Not Allow the movement!");
					return true;
				}
			}
			player.sendMessage(TextFormat.RED+"[Allplayer]+"+TextFormat.GREEN+" You don't have permission to use this commnads");
		} 

		if(command.getName().equalsIgnoreCase("allfood")){
			if(player.hasPermission("allfood.commands")){
				if(Food==true){
					Food = false;
					this.config.put("food", Boolean.valueOf(false));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Allow the eating food!");
					return true;
				}else{
					Food = true;
					this.config.put("food", Boolean.valueOf(true));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Not Allow the eating food!");
					return true;
				}
			}
			player.sendMessage(TextFormat.RED+"[Allplayer]+"+TextFormat.GREEN+" You don't have permission to use this commnads");
		} 

		if(command.getName().equalsIgnoreCase("allexplode")){
			if(player.hasPermission("allexplode.commands")){
				if(Explode==true){
					Explode = false;
					this.config.put("explode", Boolean.valueOf(false));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Allow the explosion!");
					return true;
				}else{
					Explode = true;
					this.config.put("explode", Boolean.valueOf(true));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Not Allow the explosion!");
					return true;
				}
			}
			player.sendMessage(TextFormat.RED+"[Allplayer]+"+TextFormat.GREEN+" You don't have permission to use this commnads");
		} 


		if(command.getName().equalsIgnoreCase("allpvp")){
			if(player.hasPermission("allpvp.commands")){
				if(Pvp==true){
					Pvp = false;
					this.config.put("pvp", Boolean.valueOf(false));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Allow the PVP!");
					return true;
				}else{
					Pvp = true;
					this.config.put("pvp", Boolean.valueOf(true));
					this.save();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" Not Allow the PVP!");
					return true;
				}
			}
			player.sendMessage(TextFormat.RED+"[Allplayer]+"+TextFormat.GREEN+" You don't have permission to use this commnads");
		}

		if(command.getName().equalsIgnoreCase("allgive")){
			if(player.hasPermission("allgive.commands")){
				if(args.length==0){
					player.sendMessage(TextFormat.GRAY+"/allgive id damage count");
				}
				if(args.length==2){
					Integer arg = null;
					for(Player onlinePlayers : getServer().getOnlinePlayers().values()){
						onlinePlayers.getInventory().addItem(new Item(Integer.parseInt(args[0]),arg,Integer.parseInt(args[1])));
						this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" You were received items by admin!");
					}
				}
				if(args.length==3){
					for(Player onlinePlayers : getServer().getOnlinePlayers().values()){
						onlinePlayers.getInventory().addItem(new Item(Integer.parseInt(args[0]), Integer.parseInt(args[1]),Integer.parseInt(args[2])));
						this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" You were received items by admin!");
					}
				}
			}
		}
		if(command.getName().equalsIgnoreCase("alleffect")){
			if(player.hasPermission("alleffect.commands")){
				if(args.length==0){
					player.sendMessage(TextFormat.GRAY+"/alleffect id amp dur");
				}
				if(args.length==3){
					for(Player onlinePlayers : getServer().getOnlinePlayers().values()){

						Effect effect = Effect.getEffect(Integer.parseInt(args[0]));
						effect.setAmplifier(Integer.parseInt(args[1]));
						effect.setDuration(Integer.parseInt(args[2])*20);
						onlinePlayers.addEffect(effect);
						this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" You were received effects by admin!");

					}
				}
			}
		}
		if(command.getName().equalsIgnoreCase("alleffectclear")){
			if(player.hasPermission("alleffectclear.commands")){
				for(Player onlinePlayers : getServer().getOnlinePlayers().values()){
					onlinePlayers.removeAllEffects();
					this.getServer().broadcastMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GREEN+" You were removed all effects by admin!");
				}
			}
		}
		this.save();
		return true;
	}


	@EventHandler(priority=EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event){
		if(Allplayer.BlockBreak){
			if(!event.getPlayer().hasPermission("allbreak.commands")){
				event.setCancelled(true);
				event.getPlayer().sendMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GOLD+" You can't break the block!");
			}

		}
	} //올플블럭놓기

	@EventHandler(priority=EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event){
		if(Allplayer.BlockPlace){
			if(!event.getPlayer().hasPermission("allplace.commands")){
				event.setCancelled(true);
				event.getPlayer().sendMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GOLD+" You can't place the block!");
			}
		}
	} //올플블럭캐기

	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerChat(PlayerChatEvent event){
		if(Allplayer.Chat){
			if(!event.getPlayer().hasPermission("allchat.commands")){
				event.setCancelled(true);
				event.getPlayer().sendMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GOLD+" You can't use chat!");
			}
		}
	} //올플채팅

	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerMovement(PlayerMoveEvent event){
		if(Allplayer.Movement){
			if(!event.getPlayer().hasPermission("allmove.commands")){
				event.setCancelled(true);
				event.getPlayer().sendMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GOLD+" You can't move now!");
			}
		}
	} //올플움직임



	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerEatFood(PlayerEatFoodEvent event){
		if(Allplayer.Food){
			if(!event.getPlayer().hasPermission("allfood.commands")){
				event.setCancelled(true);
				event.getPlayer().sendMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GOLD+" You can't eat anything!");
			}

		}
	}

	@EventHandler(priority=EventPriority.NORMAL)
	public void onExplosion(EntityExplosionPrimeEvent event){
		if(Allplayer.Explode){	
			event.setCancelled(true);  
		}
	}
	//올플폭발


	@EventHandler
	public void onPlayerHealth(EntityDamageEvent event){
		if(Allplayer.Pvp){
			if(event instanceof EntityDamageByEntityEvent&&((EntityDamageByEntityEvent) event).getDamager() instanceof Player&&event.getEntity() instanceof Player){
				if(!((Player) ((EntityDamageByEntityEvent) event).getDamager()).hasPermission("allpvp.commands")){
					event.setCancelled(true);
					((Player) ((EntityDamageByEntityEvent) event).getDamager()).sendMessage(TextFormat.RED+"[Allplayer]"+TextFormat.GOLD+" You can't fight Now!");
				}
			}

		}
	} //올플pvp

	//sendMessage() -> broadCastMessage() 로 변환

	public String help(){
		String command = 
				TextFormat.RED+"[AllPlayer]"+TextFormat.GREEN+"======================"+"\n"+
				TextFormat.GREEN+"/allplace"+" /allbreak"+" /allmove"+"\n"+
				TextFormat.GREEN+"/allchat"+" /allexplode"+" /allpvp"+"\n"+
				TextFormat.GREEN+"/allfood"+" /alleffectclear"+" /allplayer"+"\n"+
				TextFormat.GREEN+"/allgive id damage count "+"\n"+
				TextFormat.GREEN+"/alleffect id amp dur"+"\n"+
				TextFormat.RED+"[help]"+TextFormat.GREEN+"==========================";

		return command;
	} //명령어 도움말


	public void save() { 
		Config config = new Config(getDataFolder() + "/config.yml", Config.YAML); 
		config.setAll(this.config); 
		config.save(); 
	} //config 저장

}

class SaveData extends PluginTask<Plugin>{

	public SaveData(Allplayer owner){
		super(owner);
	}

	@Override
	public void onRun(int currentTick) {
		this.getOwner().saveDefaultConfig();
		this.getOwner().saveConfig();

	} //1초마다 config 저장
} 








