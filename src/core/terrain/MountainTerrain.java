package core.terrain;

public class MountainTerrain implements ITerrain {
	
	@Override
	public TerrainType getTerrainType() {
		return TerrainType.TERRAIN_MOUNTAIN;
	}
}
