package com.excyn.hitzads.Listeners;

public interface IAdRequestListener {
    public void onAdLoad();
    public void onAdFailedToLoad(String error);
}
