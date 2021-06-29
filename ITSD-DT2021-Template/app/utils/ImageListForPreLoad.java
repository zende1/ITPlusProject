package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import structures.basic.Card;
import structures.basic.Tile;
import structures.basic.Unit;

/**
 * This is a utility class that builds a large set of image URLs
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class ImageListForPreLoad {

	/**
	 * This method returns a very big list of relative locations of the different images
	 * that are needed by the game. Pixi.js will cache these on game load.
	 * @return
	 */
	public static Set<String> getImageListForPreLoad() {
		
		Set<String> images = new HashSet<String>();
		
		// UI Elements
		images.add("assets/game/extra/battlemap6_middleground.png");
		images.add("assets/game/extra/AttackCircle.png");
		images.add("assets/game/extra/HealthCircle.png");
		images.add("assets/game/extra/ManaCircle.png");
		images.add("assets/game/extra/ui/icon_mana.png");
		images.add("assets/game/extra/ui/icon_mana_inactive.png");
		images.add("assets/game/extra/ui/notification_quest_small.png");
		images.add("assets/game/extra/ui/general_portrait_image_hex_f1-third@2x.png");
		images.add("assets/game/extra/ui/general_portrait_image_hex_f3@2x.png");
		images.add("assets/game/extra/ui/tooltip_left@2x.png");
		images.add("assets/game/extra/ui/tooltip_right@2x.png");
		images.add("assets/game/extra/ui/button_end_turn_enemy.png");
		images.add("assets/game/extra/ui/button_primary.png");
		
		// Tiles
		images.addAll(Tile.constructTile(StaticConfFiles.tileConf).getTileTextures());
		
		// Avatars
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, -1, Unit.class).getAnimations().getAllFrames());
		
		// Deck 1 Cards
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_truestrike));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_sundrop_elixir));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_comodo_charger));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_azure_herald));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_azurite_lion));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_fire_spitter));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_hailstone_golem));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_ironcliff_guardian));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_pureblade_enforcer));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_silverguard_knight));
		
		// Deck 1 Units
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_comodo_charger, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azure_herald, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_azurite_lion, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_fire_spitter, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golem, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_ironcliff_guardian, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pureblade_enforcer, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_silverguard_knight, -1, Unit.class).getAnimations().getAllFrames());
		
		// Deck 2 Cards
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_staff_of_ykir));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_entropic_decay));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_planar_scout));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_rock_pulveriser));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_pyromancer));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_bloodshard_golem));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_blaze_hound));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_windshrike));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_hailstone_golem));
		images.addAll(getCardImagesForPreload(StaticConfFiles.c_serpenti));
		
		// Deck 2 Units
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_planar_scout, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_rock_pulveriser, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_pyromancer, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_bloodshard_golem, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_blaze_hound, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_windshrike, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_hailstone_golemR, -1, Unit.class).getAnimations().getAllFrames());
		images.addAll(BasicObjectBuilders.loadUnit(StaticConfFiles.u_serpenti, -1, Unit.class).getAnimations().getAllFrames());
		
		
		images.addAll(BasicObjectBuilders.loadEffect(StaticConfFiles.f1_projectiles).getAnimationTextures());
		images.addAll(BasicObjectBuilders.loadEffect(StaticConfFiles.f1_buff).getAnimationTextures());
		images.addAll(BasicObjectBuilders.loadEffect(StaticConfFiles.f1_inmolation).getAnimationTextures());
		images.addAll(BasicObjectBuilders.loadEffect(StaticConfFiles.f1_martyrdom).getAnimationTextures());
		images.addAll(BasicObjectBuilders.loadEffect(StaticConfFiles.f1_summon).getAnimationTextures());
		
		return images;
	}
	
	
	public static List<String> getCardImagesForPreload(String configFile) {
		Card card = BasicObjectBuilders.loadCard(configFile, 0, Card.class);
		List<String> images = new ArrayList<String>(card.getMiniCard().getAnimationFrames().length+card.getMiniCard().getCardTextures().length+card.getBigCard().getCardTextures().length);
		for (String image : card.getMiniCard().getAnimationFrames()) images.add(image);
		for (String image : card.getMiniCard().getCardTextures()) images.add(image);
		for (String image :card.getBigCard().getCardTextures()) images.add(image);
		return images;
	}
	
}
