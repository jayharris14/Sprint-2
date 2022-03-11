package concord;

public class Role
{	
	String name;
	boolean addModerator;
	boolean addChannel;
	boolean addAdmin;
	boolean removeMember;
	boolean removeModerator;
	boolean inviteUser;
	boolean removeChannel;
	boolean addMember;
	/**
	 * @return the addModerator

	/**
	 * @param addModerator the addModerator to set
	 */
	public void setAddModerator(boolean addModerator)
	{
		this.addModerator = addModerator;
	}
	/**
	 * @return the addChannell
	 *

	/**
	 * @param addChannell the addChannell to set
	 */
	public void setAddChannel(boolean addChannell)
	{
		this.addChannel = addChannell;
	}
	/**
	 * @return the addAdmin
	 */

	public void setAddAdmin(boolean addAdmin)
	{
		this.addAdmin = addAdmin;
	}
	/**
	 * @return the removeMember
	 */
	/**
	 * @param removeMember the removeMember to set
	 */
	public void setRemoveMember(boolean removeMember)
	{
		this.removeMember = removeMember;
	}
	/**
	 * @return the removeModerator
	 */
	public void setRemoveModerator(boolean removeModerator)
	{
		this.removeModerator = removeModerator;
	}
	/**
	 * @return the inviteUser
	 *
	/**
	 * @param inviteUser the inviteUser to set
	 */
	public void setInviteUser(boolean inviteUser)
	{
		this.inviteUser = inviteUser;
	}
	/**
	 * @return the removeChannel
	 */
	public void setRemoveChannel(boolean removeChannel)
	{
		this.removeChannel = removeChannel;
	}
	
	

}
