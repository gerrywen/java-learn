package ch14_01;

/**
 * program: java-learn->AbstractColleague
 * description: 抽象同事类
 * author: gerry
 * created: 2020-03-21 15:42
 **/
public abstract class AbstractColleague {
    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator _mediator) {
        this.mediator = _mediator;
    }
}
