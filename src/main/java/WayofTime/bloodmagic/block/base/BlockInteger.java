package WayofTime.bloodmagic.block.base;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Creates a block that has multiple meta-based states.
 * 
 * These states will be numbered 0 through {@code maxMeta}.
 */
@Getter
public class BlockInteger extends Block
{
    private final int maxMeta;
    private final PropertyInteger property;
    private final BlockStateContainer realStateContainer;

    public BlockInteger(Material material, int maxMeta, String propName)
    {
        super(material);

        this.maxMeta = maxMeta;
        this.property = PropertyInteger.create(propName, 0, maxMeta);
        this.realStateContainer = createStateContainer();
        setDefaultState(getBlockState().getBaseState());
    }

    public BlockInteger(Material material, int maxMeta)
    {
        this(material, maxMeta, "meta");
    }

    @Override
    protected final BlockStateContainer createBlockState() {
        return new BlockStateContainer.Builder(this).build(); // Blank to avoid crashes
    }

    @Override
    public final BlockStateContainer getBlockState() {
        return realStateContainer;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(property, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(property);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> subBlocks) {
        for (int i = 0; i < maxMeta; i++)
            subBlocks.add(new ItemStack(item, 1, i));
    }

    protected BlockStateContainer createStateContainer() {
        return new BlockStateContainer.Builder(this).add(property).build();
    }
}
