package fr.hydroxios.pingitem.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChatItemC2SPacket {

    private Component itemComponent;


    public ChatItemC2SPacket(Component itemComponent){
        this.itemComponent = itemComponent;
    }

    public ChatItemC2SPacket(FriendlyByteBuf buf) {
        this.itemComponent = buf.readComponent();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeComponent(itemComponent);
    }


    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();

            if(player != null){
                for(ServerPlayer p : player.getServer().getPlayerList().getPlayers()){
                    p.sendSystemMessage(player.getDisplayName().copy().append(": ").append(itemComponent));
                }
            }

        });
        context.setPacketHandled(true);
    }
}
