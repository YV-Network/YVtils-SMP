package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import yv.tils.smp.utils.language.LanguageFile;
import yv.tils.smp.utils.language.LanguageMessage;
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
        if (sender instanceof Player) {
            if (args.length == 0) {
                sendUsage(sender);
                return true;
            }

            String flyspeedchangeother = null;
            String walkspeedchangeother = null;

            if (args.length == 2) {
                List<String> list3 = new ArrayList();
                List<String> list4 = new ArrayList();
                list3.add("PLAYER");
                list4.add(args[1]);
                list3.add("SPEED");
                list4.add(args[0]);

                flyspeedchangeother = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.FLYSPEED_CHANGE_OTHER), list3, list4);
                walkspeedchangeother = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.WALKSPEED_CHANGE_OTHER), list3, list4);
            }

            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("SPEED");
            list2.add(args[0]);


            String flyspeedchange = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.FLYSPEED_CHANGE), list1, list2);
            String walkspeedchange = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.WALKSPEED_CHANGE), list1, list2);

            Player player = null;

            if (args.length == 1) {
                player = (Player) sender;
            }else if (args.length == 2) {
                player = Bukkit.getPlayer(args[1]);
            }else {
                sendUsage(sender);
            }

            if (args[0].equalsIgnoreCase("reset")) {
                player.setWalkSpeed(0.2f);
                player.setFlySpeed(0.1f);
                sender.sendMessage(flyspeedchangeother);
                player.sendMessage(flyspeedchange);
            } else {
                switch (args[0]) {
                    case "-10" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-1.0f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-1.0f);
                        }
                    }
                    case "-9" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.9f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.9f);
                        }
                    }
                    case "-8" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.8f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.8f);
                        }
                    }
                    case "-7" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.7f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.7f);
                        }
                    }
                    case "-6" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.6f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.6f);
                        }
                    }
                    case "-5" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.5f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.5f);
                        }
                    }
                    case "-4" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.4f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.4f);
                        }
                    }
                    case "-3" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.3f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.3f);
                        }
                    }
                    case "-2" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.2f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.2f);
                        }
                    }
                    case "-1" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(-0.1f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(-0.1f);
                        }
                    }
                    case "0" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.0f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.0f);
                        }
                    }
                    case "1" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.1f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.1f);
                        }
                    }
                    case "2" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.2f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.2f);
                        }
                    }
                    case "3" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.3f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.3f);
                        }
                    }
                    case "4" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.4f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.4f);
                        }
                    }
                    case "5" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.5f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.5f);
                        }
                    }
                    case "6" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.6f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.6f);
                        }
                    }
                    case "7" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.7f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.7f);
                        }
                    }
                    case "8" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.8f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.8f);
                        }
                    }
                    case "9" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(0.9f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(0.9f);
                        }
                    }
                    case "10" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(1.0f);
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(1.0f);
                        }
                    }
                    case "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0", "-0.1", "-0.2", "-0.3", "-0.4", "-0.5", "-0.6", "-0.7", "-0.8", "-0.9", "-1.0" -> {
                        if (player.isFlying()) {
                            sender.sendMessage(flyspeedchangeother);
                            player.sendMessage(flyspeedchange);
                            player.setFlySpeed(Float.parseFloat(args[0]));
                        } else {
                            sender.sendMessage(walkspeedchangeother);
                            sender.sendMessage(walkspeedchange);
                            player.setWalkSpeed(Float.parseFloat(args[0]));
                        }
                    }
                    default -> sendUsage(sender);
                }
            }
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/flywalkspeed <-10 - 10> or <-1.0, -0.9 - 0.9, 1.0> [player], /flywalkspeed reset [player]");
    }}