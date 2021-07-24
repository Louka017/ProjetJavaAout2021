package DAO;


import java.sql.Connection;


public class DAOFactory extends AbstractDAOFactory{
protected static final Connection conn = CONNECTION.SpectacleConnection.getInstance();
	
	public ArtisteDAO getArtisteDAO()
	{
		return new ArtisteDAO(conn);
	}

	public CategorieDAO getCategorieDAO()
	{
		return new CategorieDAO(conn);
	}

	public ClientDAO getClientDAO()
	{
		return new ClientDAO(conn);
	}
	
	public CommandeDAO getCommandeDAO()
	{
		return new CommandeDAO(conn);
	}
	
	public ConfigurationDAO getConfigurationDAO()
	{
		return new ConfigurationDAO(conn);
	}
	
	public GestionnaireDAO getGestionnaireDAO()
	{
		return new GestionnaireDAO(conn);
	}
	
	public OrganisateurDAO getOrganisateurDAO()
	{
		return new OrganisateurDAO(conn);
	}
	
	public PersonneDAO getPersonneDAO()
	{
		return new PersonneDAO(conn);
	}
	
	public PlaceDAO getPlaceDAO()
	{
		return new PlaceDAO(conn);
	}
	
	public PlanningSalleDAO getPlanningSalleDAO()
	{
		return new PlanningSalleDAO(conn);
	}

	public RepresentationDAO getRepresentationDAO()
	{
		return new RepresentationDAO(conn);
	}
	
	public ReservationDAO getReservationDAO()
	{
		return new ReservationDAO(conn);
	}
	
	public SpectacleDAO getSpectacleDAO()
	{
		return new SpectacleDAO(conn);
	}
	
	public CatalogueDAO getCatalogueDAO()
	{
		return new CatalogueDAO(conn);
	}

}

