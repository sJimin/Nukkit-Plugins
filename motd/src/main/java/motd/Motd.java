package motd;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.TextFormat;

public class Motd extends PluginBase implements Listener{

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

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(command.getName().equalsIgnoreCase("motd")){
			if(args.length==0)return false;
			if(sender.isOp()){
				if(args.length==1){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==2){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==3){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]+" "+args[2]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==4){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]+" "+args[2]+" "+args[3]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==5){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]+" "+args[2]+" "+args[3]+" "+args[4]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==6){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]+" "+args[2]+" "+args[3]+" "+args[4]+" "+args[5]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==7){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]+" "+args[2]+" "+args[3]+" "+args[4]+" "+args[5]+" "+args[6]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==8){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]+" "+args[2]+" "+args[3]+" "+args[4]+args[5]+" "+args[6]+" "+args[7]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==9){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]+" "+args[2]+" "+args[3]+" "+args[4]+args[5]+" "+args[6]+" "+args[7]+" "+args[8]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
				if(args.length==10){
					this.getServer().getNetwork().setName(TextFormat.colorize(args[0]+" "+args[1]+" "+args[2]+" "+args[3]+" "+args[4]+args[5]+" "+args[6]+" "+args[7]+" "+args[8]+" "+args[9]));
					sender.sendMessage(TextFormat.GRAY+"Server's Name is changed!");
				}
			}
		}
		return true;
	}
}
