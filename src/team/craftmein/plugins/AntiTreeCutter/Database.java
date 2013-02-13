package team.craftmein.plugins.AntiTreeCutter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "atc_data")
public class Database {

	@Id
    private int id;
    
    @NotNull
    private String playerName;
    
    @NotNull
    private String world;
    
    @NotNull
    private int minutesLeft;

	public int getMinutesLeft() {
		return minutesLeft;
	}

	public void setMinutesLeft(int minutesLeft) {
		this.minutesLeft = minutesLeft;
	}

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
}
