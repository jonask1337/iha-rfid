/**
 * Created by Jonas on 29.06.2017.
 */
public interface ProductAmountGetter {
    int getWeight(EpcTag tag);

    int getAmount(EpcTag tag);
}
