package by.training.shapes.service;


import by.training.shapes.entity.Ball;
import by.training.shapes.entity.BallRegistrar;
import by.training.shapes.service.impl.BallCalculatorServiceImpl;
import by.training.shapes.service.impl.BallConditionCheckerServiceImpl;
import by.training.shapes.service.impl.BallRegistrarServiceImpl;
import by.training.shapes.service.impl.BallServiceImpl;

/**
 * The class {@code ServiceFactory} is utility class
 * that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    /**
     * Singleton instance.
     */
    private static ServiceFactory instance;

    private final BallCalculatorService ballCalculatorService;
    private final BallConditionCheckerService ballConditionCheckerService;
    private final EntityService<Ball> ballService;
    private final RegistrarService<Ball, BallRegistrar> ballRegistrarService;

    private ServiceFactory() {
        ballCalculatorService = new BallCalculatorServiceImpl();
        ballConditionCheckerService = new BallConditionCheckerServiceImpl();
        ballService = new BallServiceImpl();
        ballRegistrarService = new BallRegistrarServiceImpl();
    }

    public BallCalculatorService getBallCalculatorService() {
        return ballCalculatorService;
    }

    public BallConditionCheckerService getBallConditionCheckerService() {
        return ballConditionCheckerService;
    }

    public EntityService<Ball> getBallService() {
        return ballService;
    }

    public RegistrarService<Ball, BallRegistrar> getBallRegistrarService() {
        return ballRegistrarService;
    }

    /**
     * Getter for singleton instance.
     *
     * @return instance of this class.
     */
    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }
}
