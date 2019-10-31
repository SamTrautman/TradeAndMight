package core;

import java.util.ArrayList;
import java.util.List;

import core.terrain.ITerrain;
import core.terrain.LandTerrain;
import core.terrain.MountainTerrain;
import core.terrain.SeaTerrain;
import core.terrain.TerrainType;

public class World implements IWorld{
	
	public static World Instance;
	
	private int SizeX = 0;
	private int SizeY = 0;
	
	private List<List<ITerrain>> TerrainMap;
	
	private World() {
		TerrainMap = new ArrayList<List<ITerrain>>();
	}
	
	public static World getInstance() {
		if (Instance == null) {
			Instance = new World();
		}
		return Instance;
	}
	
	public void createNewWorld(int NewSizeX, int NewSizeY) {
		TerrainMap.clear();
		
		SizeX = NewSizeX;
		SizeY = NewSizeY;
		
		for (int i = 0; i < SizeX; i++) {
			TerrainMap.add(new ArrayList<ITerrain>());
			
			for (int j = 0; j < SizeY; j++) {
				TerrainMap.get(i).add(new SeaTerrain());
			}
		}
	}
	
	public ITerrain getTerrain(int PosX, int PosY) {
		return TerrainMap.get(PosX).get(PosY);
	}
	
	public void setTerrain(int PosX, int PosY, TerrainType Type) {
		
		if (Type == TerrainType.TERRAIN_SEA) {
			TerrainMap.get(PosX).set(PosY, new SeaTerrain());
		}
		else if (Type == TerrainType.TERRAIN_LAND) {
			TerrainMap.get(PosX).set(PosY, new LandTerrain());
		}
		else if (Type == TerrainType.TERRAIN_MOUNTAIN) {
			TerrainMap.get(PosX).set(PosY, new MountainTerrain());
		}
	}

	@Override
	public int getSizeX() {
		return SizeX;
	}
	
	@Override
	public int getSizeY() {
		return SizeY;
	}
}
