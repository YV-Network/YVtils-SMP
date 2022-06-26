package yv.tils.smp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.LanguageSystem.LanguageMessage;
import yv.tils.smp.placeholder.StringReplacer;

import java.util.ArrayList;
import java.util.List;

/**
* @since 4.6.6
* @version 4.6.6
*/
public class FlyWalkSpeed implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {
            sendUsage(sender);
            return true;
        }

        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        list1.add("SPEED");
        list2.add(args[0]);

        String flyspeedchange = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.FLYSPEED_CHANGE), list1, list2);
        String walkspeedchange = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.WALKSPEED_CHANGE), list1, list2);

        Player player = (Player) sender;

        switch (args[0].toLowerCase()) {
            case "reset" -> {
                player.setWalkSpeed(0.2f);
                player.setFlySpeed(0.1f);
            }
            default -> {
                switch (args[0]) {
                    case "-10":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-1.0f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-1.0f);
                        }
                        break;
                    case "-9":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.9f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.9f);
                        }
                        break;
                    case "-8":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.8f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.8f);
                        }
                        break;
                    case "-7":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.7f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.7f);
                        }
                        break;
                    case "-6":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.6f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.6f);
                        }
                        break;
                    case "-5":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.5f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.5f);
                        }
                        break;
                    case "-4":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.4f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.4f);
                        }
                        break;
                    case "-3":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.3f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.3f);
                        }
                        break;
                    case "-2":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.2f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.2f);
                        }
                        break;
                    case "-1":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.1f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.1f);
                        }
                        break;
                    case "0":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.0f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.0f);
                        }
                        break;
                    case "1":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.1f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.1f);
                        }
                        break;
                    case "2":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.2f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.2f);
                        }
                        break;
                    case "3":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.3f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.3f);
                        }
                        break;
                    case "4":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.4f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.4f);
                        }
                        break;
                    case "5":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.5f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.5f);
                        }
                        break;
                    case "6":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.6f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.6f);
                        }
                        break;
                    case "7":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.7f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.7f);
                        }
                        break;
                    case "8":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.8f);
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.8f);
                        }
                        break;
                    case "9":
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.9f);
                        }else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.9f);
                        }
                        break;
                    case "10":
                        if (player.isFlying()) {
                        sender.sendMessage(flyspeedchange);
                        player.setFlySpeed(1.0f);
                    } else {
                        sender.sendMessage(walkspeedchange);
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
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(Float.parseFloat(args[0]));
                        } else {
                            sender.sendMessage(walkspeedchange);
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
                            sender.sendMessage(flyspeedchange);
                            player.setFlySpeed(Float.parseFloat(args[0]));
                        } else {
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(Float.parseFloat(args[0]));
                        }
                        break;
                    default:
                        sendUsage(sender);
                        break;
                }}}
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/flywalkspeed <-10 - 10> or <-1.0, -0.9 - 0.9, 1.0>, /flywalkspeed reset");
    }}