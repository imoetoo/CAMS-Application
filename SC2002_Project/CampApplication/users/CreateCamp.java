package CampApplication.users;

import CampApplication.Camps.*;

/**
 * An interface for Staff to create camps and manage the details around it
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface CreateCamp {

    /**
     * create a camp 
     * @return return the camp staff created
     */
    public Camp createCamp();
    /**
     * edit a camp 
     * @return return the camp staff edited
     */
    public Camp editCamp(Camp camp);
    /**
     * delete a camp 
     */
    public void deleteCamp(Camp camp);

}
