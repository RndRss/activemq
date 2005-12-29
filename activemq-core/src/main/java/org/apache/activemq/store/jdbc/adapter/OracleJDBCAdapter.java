/**
 *
 * Copyright 2005-2006 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.store.jdbc.adapter;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.activemq.store.jdbc.StatementProvider;

/**
 * Implements all the default JDBC operations that are used
 * by the JDBCPersistenceAdapter.
 * <p/>
 * Subclassing is encouraged to override the default
 * implementation of methods to account for differences
 * in JDBC Driver implementations.
 * <p/>
 * The JDBCAdapter inserts and extracts BLOB data using the
 * getBytes()/setBytes() operations.
 * <p/>
 * The databases/JDBC drivers that use this adapter are:
 * <ul>
 * <li></li>
 * </ul>
 *
 * @version $Revision: 1.2 $
 */
public class OracleJDBCAdapter extends DefaultJDBCAdapter {

    public static StatementProvider createStatementProvider() {
        DefaultStatementProvider answer = new DefaultStatementProvider();
        answer.setLongDataType("NUMBER");
        return answer;
    }
    
    public OracleJDBCAdapter() {
        this(createStatementProvider());
    }

    public OracleJDBCAdapter(StatementProvider provider) {
        super(provider);        
    }
    
    protected byte[] getBinaryData(ResultSet rs, int index) throws SQLException {

        // Get as a BLOB
        Blob aBlob = rs.getBlob(1);
        return aBlob.getBytes(1, (int) aBlob.length());
    }
}
