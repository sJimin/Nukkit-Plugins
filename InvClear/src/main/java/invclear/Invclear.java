package invclear;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Invclear extends PluginBase implements Listener{

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
		if(!(sender instanceof Player))return true;
		if(args.length == 0)return false;
		Player target = this.getServer().getPlayer(args[0]);
		if (command.getName().equals("ci")) {
			sender.sendMessage(TextFormat.RED+target.getName()+"'s inventory is clear!");
			target.sendMessage(TextFormat.RED+"Your inventory is reset by [OP]"+sender.getName());
			target.getInventory().clearAll();
		}
		
		
		
		return true;
	
}
}


