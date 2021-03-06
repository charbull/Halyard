/*
 * Copyright 2018 Merck Sharp & Dohme Corp. a subsidiary of Merck & Co.,
 * Inc., Kenilworth, NJ, USA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.msd.gin.halyard.strategy;

import static junit.framework.TestCase.*;
import org.eclipse.rdf4j.common.iteration.CloseableIteration;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.algebra.evaluation.TripleSource;
import org.eclipse.rdf4j.query.algebra.evaluation.federation.FederatedService;
import org.eclipse.rdf4j.query.algebra.evaluation.federation.FederatedServiceResolver;
import org.junit.Test;

/**
 * @author Adam Sotona (MSD)
 */
public class HalyardStrategyServiceTest {

    @Test
    public void testGetService() {
        assertNull(new HalyardEvaluationStrategy(getTripleSource(), null, new FederatedServiceResolver() {
            @Override
            public FederatedService getService(String serviceUrl) throws QueryEvaluationException {
                return null;
            }
        }, 0).getService(null));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testServiceEvaluateFail() {
        new HalyardEvaluationStrategy(getTripleSource(), null, null, 0).evaluate(null, null, null);
    }

    private TripleSource getTripleSource() {
        return new TripleSource() {
            @Override
            public CloseableIteration<? extends Statement, QueryEvaluationException> getStatements(Resource subj, IRI pred, Value obj, Resource... contexts) throws QueryEvaluationException {
                throw new QueryEvaluationException();
            }

            @Override
            public ValueFactory getValueFactory() {
                return SimpleValueFactory.getInstance();
            }
        };
    }
}
