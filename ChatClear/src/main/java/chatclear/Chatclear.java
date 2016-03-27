package chatclear;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

public class Chatclear extends PluginBase implements Listener {

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getLogger().info("Chating clear Plugin Upload success");

	}

	@Override
	public void onDisable() {
		this.getLogger().info("Chating clear Plugin is closed by closing server");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equals("cc")) {
			for(int chat=0;chat<100;chat++){
				this.getServer().broadcastMessage(" ");
			}
			this.getServer().broadcastMessage("chat clear!");
		}
        if (command.getName().equals("cp")){
        	if(!(sender instanceof Player))return true;
        	for(int chat=0;chat<100;chat++){
        	sender.sendMessage(" ");	
        	}
        	sender.sendMessage("chat clear!");
        }
		

		return true;

	}
	
    

}
