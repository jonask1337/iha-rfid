/**
 * Created by Jonas on 29.06.2017.
 */
public class ProductAmountGetterMock implements ProductAmountGetter{

    @Override
    public int getWeight(EpcTag tag){
        return 250;
    }
    @Override
    public int getAmount(EpcTag tag){
        double factor;
        switch (tag.getObjectClass()) {
            case 6:
                factor = 1;
                break;
            case 3:
                factor = 1.5;
                break;
            default:
                factor = 1;
        }

        return (int) (getWeight(tag)*factor);
    }

}
