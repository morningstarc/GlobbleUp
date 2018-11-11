package Model;

import java.util.ArrayList;

public interface Subject {
    ArrayList<IObservable>  observables = new ArrayList<>();
    public void NotifyObservers(String event);
    public void RegisterObserver(IObservable observer);
    public void DeregisterObserver(IObservable observer);
}
