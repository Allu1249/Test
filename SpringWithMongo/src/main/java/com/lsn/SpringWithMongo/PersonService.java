package com.lsn.SpringWithMongo;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

@Repository
public class PersonService extends Query {

	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "person";

	private LinkedHashMap<String, Criteria> criteria = new LinkedHashMap<String, Criteria>();

	public void addPerson(Person person) {
		/*
		 * if (!mongoTemplate.collectionExists(Person.class)) {
		 * mongoTemplate.createCollection(Person.class); }
		 * person.setId(UUID.randomUUID().toString());
		 */
		mongoTemplate.insert(person, "person");
	}

	public List<Person> listPerson() {
		return mongoTemplate.findAll(Person.class, "person");
	}

	public void deletePerson(Person person) {
		mongoTemplate.remove(person, "person");
	}

	public void updatePerson(Person person) {
		mongoTemplate.insert(person, "person");
	}

	public List<Person> search(String name) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				.matchingPhrase(name);
		Sort s = new Sort(Direction.DESC, "name");
		Query query = TextQuery.queryText(criteria).with(s);
		List<Person> person1 = mongoTemplate
				.find(query, Person.class, "person");
		return person1;
	}

/*	public List<Person> search(String name) {

		DBObject object = getCriteriaObject(name);
		// BasicDBObject cmdBody = new BasicDBObject("aggregate",
		// COLLECTION_NAME);

		// Criteria criteria = new Criteria(object.toString());
		Criteria criteria = new Criteria();
		criteria.where("name").is(name);

		criteria.and(object.toString());
		Query query = new Query(criteria);

		// ArrayList<BasicDBObject> pipeline = new ArrayList<BasicDBObject>();
		// pipeline.add(new BasicDBObject("$match", object));
		// cmdBody.put("pipeline", pipeline);
		// CommandResult commandResult = mongoTemplate.executeCommand(cmdBody);
		// if (commandResult != null) {
		// if (commandResult.get("result") != null) {
		// @SuppressWarnings("unchecked")
		// List<DBObject> result = (List<DBObject>) commandResult
		// .get("result");
		// }
		// }
		List<Person> person1 = mongoTemplate.find(query, Person.class);
		return person1;
	}

	// db.person.find( {$text: {$search: '\"gud good\"'}})

	public DBObject getCriteriaObject(String name) {

		BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
		if (!name.isEmpty()) {

			String n = join(name);
			n = "\"" + n + "\"";
			builder.add("$search", n);
		}

		return new BasicDBObject("$text", builder.get());
	}

	private String join(String name) {

		List<String> result = new ArrayList<String>();
		result.add(name);

		return StringUtils.collectionToDelimitedString(result, " ");
	}
	
	@Override
	public Query addCriteria(Criteria criteria) {
		CriteriaDefinition existing = this.criteria.get(criteria.getKey());
		String key = criteria.getKey();
		if (existing == null) {
			this.criteria.put(key, criteria);
		} else {
			throw new InvalidMongoDbApiUsageException(
					"Due to limitations of the com.mongodb.BasicDBObject, "
							+ "you can't add a second '" + key + "' criteria. "
							+ "Query already contains '"
							+ existing.getCriteriaObject() + "'.");
		}
		return this;
	}*/

}
