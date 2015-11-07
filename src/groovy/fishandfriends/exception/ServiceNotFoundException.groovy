package fishandfriends.exception

/**
 * Created by Alexandre on 07/11/2015.
 */
class ServiceNotFoundException extends RuntimeException {

    public ServiceNotFoundException(String s){
        super(s)
    }
}
