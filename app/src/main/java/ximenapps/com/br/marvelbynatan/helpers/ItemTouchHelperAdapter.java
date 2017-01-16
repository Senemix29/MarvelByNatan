package ximenapps.com.br.marvelbynatan.helpers;

/**
 * Created by Natan Ximenes on 15/01/2017.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
