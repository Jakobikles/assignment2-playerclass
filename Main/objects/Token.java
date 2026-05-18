package objects;

import Locations.*;

public class Token {
    public Location currentLocation;
    public boolean onRole;
    public Role currentRole;
    public Token (){

    }

    public void moveToLocation(Location l){
        this.currentLocation = l;
    }

    public Location getCurrentLocation(){
        return this.currentLocation;
    }
    public void placeOnRoleSpot(Role r){
        this.onRole = true;
        this.currentRole = r;
    }
    public void removeFromRoleSpot(){
        this.onRole = false;
        this.currentRole = null;
    }
    public void setOnRole(boolean onRole){
        this.onRole = onRole;
    }
}
