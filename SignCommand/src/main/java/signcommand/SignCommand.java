package signcommand;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.TextFormat;

public class SignCommand extends PluginBase implements Listener{

	@Override
	public void onEnable() {
		PluginDescription pd = getDescription();
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info(pd.getName()+" version"+pd.getVersion()+" is enabled");

	}

	@Override
	public void onDisable() {
		PluginDescription pd = getDescription();
		this.getLogger().info(pd.getName()+" version"+pd.getVersion()+" is Disabled");
	}

	@EventHandler
	public void onSignChange(SignChangeEvent event){
		Player player = event.getPlayer();
		if(event.getLine(0).equalsIgnoreCase("command")){
			if(event.getLine(1).isEmpty()){
				player.sendMessage(TextFormat.GREEN+"Write Command in the second|third|forth Textline!");
				event.setCancelled(true);
			}else{
				event.setLine(0,TextFormat.RED+"[Command]");
				player.sendMessage(TextFormat.GREEN+"SignCommand is Created");
			}	
		}
	}


	@EventHandler(priority=EventPriority.NORMAL)
	public void PlayerInteract(PlayerInteractEvent event){
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if((event.getBlock().getId()!=63)&&(event.getBlock().getId()!=68))return;
		BlockEntity blockEntity = event.getBlock().getLevel().getBlockEntity(new Vector3(event.getBlock().getX(),event.getBlock().getY(),event.getBlock().getZ()));
		if(!(blockEntity instanceof BlockEntitySign))return;	
		BlockEntitySign sign = (BlockEntitySign)blockEntity;
		if(sign.getText()[0].equalsIgnoreCase(TextFormat.RED+"[Command]")){
			this.getServer().dispatchCommand(getServer().getConsoleSender(), sign.getText()[1]+ " "+sign.getText()[2]+" "+sign.getText()[3]);
			event.setCancelled(true);
		}else{
			player.sendMessage(TextFormat.GREEN+"You must write command in the First line");
		}

	}





}
