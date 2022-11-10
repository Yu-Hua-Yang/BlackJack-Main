//enum and the final values of each individual card to identify cards
public enum Value {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);
    
    private final int values;

    private Value(final int values) {
        this.values = values;
    } 

    public int getValues() {
        return this.values;
    }
}

