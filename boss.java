public class Boss {
    private int health;
    private int bossHealth;
    private String[] words;
    private String[] meanings;
    private boolean mcq;

    public Boss(String[] w, String[] m, boolean isMcq) {
        this.health = 5;
        this.bossHealth = 5;
        this.words = w;
        this.meanings = m;
        this.mcq = isMcq;
    }
}