package sk.eea.triplestore.bench.stores;

import org.openrdf.repository.manager.RemoteRepositoryManager;
import org.openrdf.repository.manager.RepositoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sk.eea.triplestore.bench.Settings;

public class OpenRdfStore extends AbstractSailStore {
	private static final Logger logger = LoggerFactory.getLogger(OpenRdfStore.class);

	public void initialize(Settings settings) throws Exception  {
		String repositoryId = settings.getProviderData("openrdf.repository.id");
		RepositoryManager repoManager = new RemoteRepositoryManager(settings.repository_url);
		repoManager.initialize();
		repository = repoManager.getRepository(repositoryId);
		if (repository == null) {
			throw new Exception("No repository " + repositoryId + " exists");
		}
		repository.initialize();
		
	}

	public String getDescription() {
		return "OpenRDF/Sesame";
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}
}
