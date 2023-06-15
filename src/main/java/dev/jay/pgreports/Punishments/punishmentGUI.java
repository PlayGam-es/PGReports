package dev.jay.pgreports.Punishments;

import dev.jay.pgreports.Menus.PaginatedMenu;
import dev.jay.pgreports.Menus.PlayerMenuUtility;
import dev.jay.pgreports.PGReports;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class punishmentGUI extends PaginatedMenu {

    HashMap<Integer, ItemStack> items = new HashMap<>();

    private final PGReports plugin;
    public punishmentGUI(PlayerMenuUtility playerMenuUtility, PGReports plugin) {
        super(playerMenuUtility);
        this.plugin = plugin;
    }

    @Override
    public String getMenuName() {
        return Color(Hex("&6CoinShop"));
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', getMenuName()))){

            e.setCancelled(true);


        }




    }

    @Override
    public void setMenuItems() {

        String warnItemConfig = plugin.getConfig().getString("PunishmentGUI.Items.warnItem");
        String warnItemConfigDisplay = plugin.getConfig().getString("PunishmentGUI.Items.WarnItem.displayname");
        List<String> warnItemLore = plugin.getConfig().getStringList("PunishmentGUI.Items.WarnItem.Lore");

        ItemStack warnItem = new ItemStack(Material.valueOf(warnItemConfig), 1);

        ItemMeta warnItemMeta = warnItem.getItemMeta();

        warnItemMeta.setDisplayName(Color(Hex(warnItemConfigDisplay)));

        ArrayList<String> lore = new ArrayList<>();
        for (String l : warnItemLore){
            lore.add(Color(Hex(l)));
        }
        warnItemMeta.setLore(lore);

        warnItem.setItemMeta(warnItemMeta);


    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }

    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
    public static String Hex(String message) {
        Matcher matcher = HEX_PATTERN.matcher(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.of(matcher.group(1)).toString());
        }

        return matcher.appendTail(buffer).toString();
    }

    public boolean isSimilarCheck(ItemStack first,ItemStack second){

        boolean similar = false;

        if(first == null || second == null){
            return similar;
        }

        boolean sameDurability = (first.getDurability() == second.getDurability());
        boolean sameAmount = (first.getAmount() == second.getAmount());
        boolean sameHasItemMeta = (first.hasItemMeta() == second.hasItemMeta());
        boolean sameEnchantments = (first.getEnchantments().equals(second.getEnchantments()));
        boolean sameItemMeta = true;

        if(sameHasItemMeta) {
            sameItemMeta = Bukkit.getItemFactory().equals(first.getItemMeta(), second.getItemMeta());
        }

        if(sameDurability && sameAmount && sameHasItemMeta && sameEnchantments && sameItemMeta){
            similar = true;
        }

        return similar;

    }
}
