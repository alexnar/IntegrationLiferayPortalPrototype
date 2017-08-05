create table ProjectService_InfrastructureEntityProject (
	infrastructureEntityProjectPKId LONG not null primary key,
	organizationId LONG,
	infrastructureEntityName VARCHAR(75) null,
	infrastructureEntityProjectId VARCHAR(75) null
);