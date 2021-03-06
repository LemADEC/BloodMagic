package WayofTime.bloodmagic.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import WayofTime.bloodmagic.entity.projectile.EntityMeteor;

public class MeteorRenderFactory implements IRenderFactory<EntityMeteor>
{
    @Override
    public Render<? super EntityMeteor> createRenderFor(RenderManager manager)
    {
        return new RenderEntityMeteor(manager);
    }
}
