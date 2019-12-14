package controller.KassaState;

/**
 * @Author Noa Andries
 **/
public class OnHoldState implements State {
    KassaOverviewController kassaOverviewController;
    public OnHoldState(KassaOverviewController kassaOverviewController)
    {
        this.kassaOverviewController = kassaOverviewController;
    }
}
