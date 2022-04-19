package yv.tils.smp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import yv.tils.smp.placeholder.LanguagePlaceholder;

public class FlyWalkSpeed implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(args.length == 0) {
            sendUsage(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reset" -> {
            player.setWalkSpeed(0.2f);
            player.setFlySpeed(0.1f);
            }
            default -> {

                switch (args[0]) {
                    case "-10":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-1.0f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-1.0f);
                        }
                        break;
                    case "-9":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.9f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.9f);
                        }
                        break;
                    case "-8":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.8f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.8f);
                        }
                        break;
                    case "-7":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.7f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.7f);
                        }
                        break;
                    case "-6":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.6f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.6f);
                        }
                        break;
                    case "-5":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.5f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.5f);
                        }
                        break;
                    case "-4":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.4f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.4f);
                        }
                        break;
                    case "-3":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.3f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.3f);
                        }
                        break;
                    case "-2":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.2f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.2f);
                        }
                        break;
                    case "-1":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(-0.1f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(-0.1f);
                        }
                        break;
                    case "0":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.0f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.0f);
                        }
                        break;
                    case "1":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.1f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.1f);
                        }
                        break;
                    case "2":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.2f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.2f);
                        }
                        break;
                    case "3":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.3f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.3f);
                        }
                        break;
                    case "4":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.4f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.4f);
                        }
                        break;
                    case "5":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.5f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.5f);
                        }
                        break;
                    case "6":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.6f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.6f);
                        }
                        break;
                    case "7":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.7f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.7f);
                        }
                        break;
                    case "8":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.8f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.8f);
                        }
                        break;
                    case "9":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(0.9f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(0.9f);
                        }
                        break;
                    case "10":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(1.0f);
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(1.0f);
                        }
                        break;
                    case "0.1":
                    case "0.2":
                    case "0.3":
                    case "0.4":
                    case "0.5":
                    case "0.6":
                    case "0.7":
                    case "0.8":
                    case "0.9":
                    case "1.0":
                        if (player.isFlying()) {
                            sender.sendMessage("You changed your Fly Speed to " + args[0]);
                            player.setFlySpeed(Float.parseFloat(args[0]));
                        } else {
                            sender.sendMessage("You changed your Walk Speed to " + args[0]);
                            player.setWalkSpeed(Float.parseFloat(args[0]));
                        }
                        break;
                    case "-0.1":
                    case "-0.2":
                    case "-0.3":
                    case "-0.4":
                    case "-0.5":
                    case "-0.6":
                    case "-0.7":
                    case "-0.8":
                    case "-0.9":
                    case "-1.0":
                        if (player.isFlying()) {
                            player.setFlySpeed(Float.parseFloat(args[0]));
                        } else {
                            player.setWalkSpeed(Float.parseFloat(args[0]));
                        }
                        break;
                    default:
                        sendUsage(sender);
                        break;
                }

            }
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguagePlaceholder.CommandUsage() + ChatColor.BLUE +
                "/flywalkspeed <-10 - 10> or <-1.0, -0.9 - 0.9, 1.0>, /flywalkspeed reset");
    }
}
