package fr.hydroxios.pingitem;

import fr.hydroxios.pingitem.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PingItem.MODID)
public class PingItem {

    public static final String MODID = "pingitem";

    public PingItem() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void commonSetup(FMLCommonSetupEvent e){
        e.enqueueWork(NetworkHandler::registerPackets);
    }

}
