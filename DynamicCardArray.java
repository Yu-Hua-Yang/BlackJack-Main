//Class where I will call back to when I use DynamicCardArray to use methods to help with game
public class DynamicCardArray {
    private Card[] cards;
	private int next;

	public DynamicCardArray() {
		this.cards = new Card[200];
		this.next = 0;
	}

    //adds a card into a dynamiccardarray
	public void add(Card s)
    {
        if(this.next >= this.cards.length){
            tooSmall();
        }
        this.cards[this.next] = s;
        this.next++;
    }

    //since there is 200 spots in the array it returns a integer for the length of the filled array spots currently
    public int length(){
        return this.next;
    }

    //checks if your card you are looking for is amongst the cards available
    public boolean contains(Card s)
    {
        for(int i = 0; i < this.next; i++){
            if(this.cards[i] == (s))
            {
               return true; 
            }
        }
        return false;
    }

    //it retrieves the card at a specific inserted index
    public Card get(int i){
        if(i >= this.next)
        {
            throw new ArrayIndexOutOfBoundsException("no value");
        }
        return this.cards[i];
    }

    //it sets a card in the array with a new value at a specific index inserted by the user
    public void set(int i, Card newValue){
        this.cards[i] = newValue;
    }

    //it removes a card for the dynamiccardarray
    public void remove(int i){
        if(i > this.next){
            throw new ArrayIndexOutOfBoundsException("no value");
        }
        else{
            for(int j = i; j < this.next; j++){
            this.cards[j] = this.cards[j+1];
            }
            this.next--;
        }
    }

    //insert inserts a card before the index inserted that shifts everything to the right one
    public void insert(int i, Card inserted){
        if(i > this.next){
            throw new ArrayIndexOutOfBoundsException("no value");
        }
        else{
            for(int j = this.next-1; j >= i; j--){
                this.cards[j+1] = this.cards[j];
            }
            this.cards[i] = inserted;
            this.next++;
        }
    }

    //if the card[] length is too small it will double the card[]'s length
    private void tooSmall(){
            Card[] bigger = new Card[this.cards.length*2];
            for(int i=0; i > this.cards.length; i++){
                bigger[i] = this.cards[i];
            }
            this.cards = bigger;
    }  

    //removes a card from the top of the deck or index 0 basically drawing a card
    public Card getCardFromTop(){
        Card FirstCard = this.cards[0];
        remove(0); 
        return FirstCard;
    }
}
    


