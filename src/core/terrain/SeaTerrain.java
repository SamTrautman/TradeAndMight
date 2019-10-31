package core.terrain;

public class SeaTerrain implements ITerrain {

	@Override
	public TerrainType getTerrainType() {
		return TerrainType.TERRAIN_SEA;
	}
}
