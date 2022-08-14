package fop.w9colony;

public class Penguin {
    private final int birthYear;
    private final String name;
    private final Gender gender;
    private Fish favoriteFish;

    public Penguin(int birthYear, String name, Gender gender, Fish favoriteFish) {
        this.birthYear = birthYear;
        this.name = name;
        this.gender = gender;
        this.favoriteFish = favoriteFish;
    }

     public boolean equals(Object other) {
        if(other==null)
            return false;

        if(other instanceof Penguin){
            boolean namesNullChecker= false;
            boolean genderNullChecker = false;
            if(((Penguin) other).getName()==null && this.name!=null){
                return false;
            }
            if(((Penguin) other).getName()==null && this.name==null){
                namesNullChecker=true;
            }
            if(((Penguin) other).getGender()==null && this.gender!=null){

                return false;
            }
            if(((Penguin) other).getGender()==null && this.gender==null){
                genderNullChecker=true;

            }

            boolean theyAreSame = (this.birthYear== ((Penguin) other).getBirthYear() ) &&
                    (this.name==((Penguin) other).getName()||namesNullChecker)
                    &&(this.gender.equals(((Penguin) other).getGender())||genderNullChecker );
            return theyAreSame;
        }
        return false;
    }

    public int hashCode() {
        int x ;
        if(gender==Gender.FEMALE){
            x = 7;
        }else {
            x = 49;
        }
        return 343*this.birthYear+2401*name.hashCode()+ x;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Fish getFavoriteFish() {
        return favoriteFish;
    }

    public void setFavoriteFish(Fish favoriteFish) {
        this.favoriteFish = favoriteFish;
    }

}
