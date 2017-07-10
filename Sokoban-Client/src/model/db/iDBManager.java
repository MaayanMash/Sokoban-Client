package model.db;

import java.util.List;

/**
 * define interface for DB manager
 * @see public void add(Object o) Adds a record to the appropriate table;
 * @see public void update(Object o) Updating an entry in the table;
 * @see public List selectScore(String query) Returns List of query results
 *
 */
public interface iDBManager {
	/**
	 * Adds a record to the appropriate table;
	 * @param o Object that you want to add to the table
	 * @throws Exception wrong input
	 */
	public void add(Object o) throws Exception;
	/**
	 * Updating an entry in the table;
	 * @param o Object that want to update
	 * @throws Exception
	 */
	public void update(Object o) throws Exception;
	/**
	 * Running query on DB
	 * @param query The query that you want to run on DB
	 * @return A list of objects returned from the query
	 */
	public List selectScore(String query);

}
