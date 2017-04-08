/**
 * Created by werdn on 4/7/17.
 */
class Players {
    Player comp;
    Player user;

    public Player getComp() {
        return comp;
    }

    public Player getUser() {
        return user;
    }

    public Players() {
        this.comp = new Player();
        this.user = new Player();
    }
}
