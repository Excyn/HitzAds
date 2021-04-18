package com.excyn.hitzads.Listeners;

public interface IAdEventListener {

    public void onAdLoad();
    public void onAdFailedToLoad(String error);
    public void onAdOpened();
    public void onAdClicked();
    public void onAdSkipped();
    public void onAdClosed();
}
