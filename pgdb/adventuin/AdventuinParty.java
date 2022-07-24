package pgdp.adventuin;

import pgdp.color.RgbColor8Bit;

import java.util.*;

import java.util.stream.Collectors;

public final class AdventuinParty {
    public static Map<HatType, List <Adventuin>> groupByHatType(List<Adventuin> a){
        return a.stream()
                .collect(Collectors.groupingBy(Adventuin::getHatType));
    }
    public static void printLocalizedChristmasGreetings(List<Adventuin> a){
        a.stream()
                .sorted(Comparator.comparing(Adventuin::getHeight))
                .forEach(penguin -> {
                    System.out.println(penguin.getLanguage().
                            getLocalizedChristmasGreeting(penguin.getName()));
                });
    }
    public static Map<HatType, List<Adventuin>> getAdventuinsWithLongestNamesByHatType(List<Adventuin> a) {
        final Map<HatType, List<Adventuin>> result = new HashMap<>();
        a.forEach(e -> {
            if(result.containsKey(e.getHatType())){
                int b=result.get(e.getHatType()).get(0).getName().length();
                if(e.getName().length()==b){
                    result.get(e.getHatType()).add(e);
                }else if(e.getName().length()>b){
                    List<Adventuin> newList = new ArrayList();
                    newList.add(e);
                    result.put(e.getHatType(),newList);
                }
            }else{
                List<Adventuin> newList = new ArrayList();
                newList.add(e);
                result.put(e.getHatType(),newList);
            }
        });
        return result;
    }
    private static Double helper(List<Adventuin> adventuins) {
        List<Double> ans = new ArrayList<>();
        adventuins.forEach(b->{
            RgbColor8Bit color = b.getColor().toRgbColor8Bit();
            Double whatToAdd= (color.getBlue()*0.0722+color.getRed()*0.2126+color.getGreen()*0.7152)/255;
            ans.add(whatToAdd);
        });
        return ans.stream().mapToDouble(Double::doubleValue).sum() / ans.size();
    }
   
    private static Double helper3(List<Adventuin> adventuins){
        ArrayList<Integer> pluses = new ArrayList<>();
        ArrayList<Integer> minuses = new ArrayList<>();
        int b = adventuins.size();
        int diff;
        for(int i = 0; i< adventuins.size();i++){
            if(i==0) {
                diff = adventuins.get(0).getHeight() - adventuins.get(b - 1).getHeight();
                if(diff>0){
                    pluses.add(diff);
                }
                if(diff<0){
                    minuses.add(diff);
                }
            }else{
                diff = adventuins.get(i).getHeight() - adventuins.get(i - 1).getHeight();
                if(diff>0){
                    pluses.add(diff);
                }
                if(diff<0){
                    minuses.add(diff);
                }
            }
        }
        Double plusesSum=0.0;
        Double minusesSum=0.0;
        for(int i=0;i<pluses.size();i++){
            plusesSum+=pluses.get(i);
        }
        for(int i=0;i<minuses.size();i++){
            minusesSum+=minuses.get(i);
        }
        if(pluses.size()!= 0 ){
            plusesSum=plusesSum/(pluses.size());
        }
        if(minuses.size()!=0){
            minusesSum=minusesSum/(minuses.size());
        }
        minusesSum=Math.abs(minusesSum);
        plusesSum=Math.abs(plusesSum);
        return minusesSum+plusesSum;
    }
    public static Map<Integer, Double> getAverageColorBrightnessByHeight(List<Adventuin> a){
        final Map<Integer, List<Adventuin>> result = new HashMap<>();
        a.forEach(e -> {
            int b;
            if(e.getHeight()%10>=5){
                b=e.getHeight()+6;
                b=b/10;
                b=b*10;
            }else {
                b=e.getHeight()/10;
                b=b*10;
            }
            if(result.containsKey(b)){
                result.get(b).add(e);
            }else{
                List<Adventuin> newList = new ArrayList();
                newList.add(e);
                result.put(b,newList);
            }
        });
        final Map<Integer, Double> finalResult = new HashMap<>();
        Set<Integer> numbers = result.keySet();
        numbers.forEach(num->{
            finalResult.put(num,helper(result.get(num)));
        });
        return finalResult;
    }

    public static  Map<HatType, Double> getDiffOfAvgHeightDiffsToPredecessorByHatType(List<Adventuin> penguins){

        Map<HatType,List<Adventuin>> adventuinsBeforeSorting=new HashMap<>();
        penguins.forEach(penguin->{
            if(adventuinsBeforeSorting.containsKey(penguin.getHatType())){
                    adventuinsBeforeSorting.get(penguin.getHatType()).add(penguin);
            }else{
                List<Adventuin> newList = new ArrayList();
                newList.add(penguin);
                adventuinsBeforeSorting.put(penguin.getHatType(),newList);
            }
        });

        List<Adventuin> noHat=adventuinsBeforeSorting.get(HatType.NO_HAT);
        List<Adventuin> reinder= adventuinsBeforeSorting.get(HatType.REINDEER);
        List<Adventuin> santa= adventuinsBeforeSorting.get(HatType.SANTA_CLAUS);
        List<Adventuin> fishy= adventuinsBeforeSorting.get(HatType.FISHY_HAT);
        Map<HatType, Double> result = new HashMap<>();
        if(noHat!= null ){
            result.put(HatType.NO_HAT,AdventuinParty.helper3(noHat));
        }
        if(reinder!= null){
            result.put(HatType.REINDEER,AdventuinParty.helper3(reinder));
        }
        if(santa!= null){
            result.put(HatType.SANTA_CLAUS,AdventuinParty.helper3(santa));
        }
        if(fishy!= null){
            result.put(HatType.FISHY_HAT,AdventuinParty.helper3(fishy));
        }
        return result;
    }


}

