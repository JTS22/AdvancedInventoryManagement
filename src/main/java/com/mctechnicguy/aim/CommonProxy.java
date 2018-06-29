package com.mctechnicguy.aim;

import com.mctechnicguy.aim.container.ContainerAIMCore;
import com.mctechnicguy.aim.network.*;
import com.mctechnicguy.aim.tileentity.TileEntityAIMCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class CommonProxy implements IGuiHandler {

	public void registerRenderers() {
	}

	public void registerKeys() {
	}

	public void registerPackets() {
		PacketHelper.wrapper.registerMessage(new IMessageHandler<PacketOpenInfoGUI, IMessage>() {
			@Nullable
            @Override
			public IMessage onMessage(PacketOpenInfoGUI message, MessageContext ctx) {
				return null;
			}
		}, PacketOpenInfoGUI.class, 1, Side.CLIENT);
		PacketHelper.wrapper.registerMessage(new IMessageHandler<PacketHotbarSlotChanged, IMessage>() {
			@Nullable
            @Override
			public IMessage onMessage(PacketHotbarSlotChanged message, MessageContext ctx) {
				return null;
			}
		}, PacketHotbarSlotChanged.class, 2, Side.CLIENT);
		PacketHelper.wrapper.registerMessage(new IMessageHandler<PacketUpdateOverlayInfo, IMessage>() {
			@Nullable
			@Override
			public IMessage onMessage(PacketUpdateOverlayInfo message, MessageContext ctx) {
				return null;
			}
		}, PacketUpdateOverlayInfo.class, 3, Side.CLIENT);
        PacketHelper.wrapper.registerMessage(new IMessageHandler<PacketOpenNetworkCoreList, IMessage>() {
            @Nullable
            @Override
            public IMessage onMessage(PacketOpenNetworkCoreList message, MessageContext ctx) {
                return null;
            }
        }, PacketOpenNetworkCoreList.class, 4, Side.CLIENT);
		PacketHelper.wrapper.registerMessage(PacketKeyPressed.PacketKeyPressedHandler.class, PacketKeyPressed.class, 0, Side.SERVER);
	}

	public void registerFluid(BlockFluidClassic block, String name) {}

	public boolean playerEqualsClient(UUID client) {
		return true;
	}

	@Nullable
    public EntityPlayer getClientPlayer() {
		return null;
	}

	@Nullable
    @Override
	public Object getServerGuiElement(int ID, @Nonnull EntityPlayer player, @Nonnull World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		if (entity != null) {
			switch (ID) {
			case AdvancedInventoryManagement.guiIDCore:
				if (entity instanceof TileEntityAIMCore) {
					return new ContainerAIMCore(player.inventory, (TileEntityAIMCore) entity);
				}
			}
		}
		return null;
	}

	@Nullable
    @Override
	public Object getClientGuiElement(int ID, @Nonnull EntityPlayer player, @Nonnull World world, int x, int y, int z) {
	    return getServerGuiElement(ID, player, world, x, y, z);
	}
	
	public void addScheduledTask(@Nonnull Runnable run, @Nonnull MessageContext ctx) {
		ctx.getServerHandler().player.getServer().addScheduledTask(run);
	}

	public EntityPlayer getPlayer(@Nonnull MessageContext ctx) {
		return ctx.getServerHandler().player;
	}

	public void addPreBlockPagesToGuide() {

    }

    @Nullable
    public String tryToLocalizeString(String format, Object... args) {
	    return null;
    }

}
