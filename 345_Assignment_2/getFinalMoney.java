public class getFinalMoney implements Action {
    @Override
    public int performAction (Player p){
        return p.money + p.credits + (p.rank * 5);
    }
}
