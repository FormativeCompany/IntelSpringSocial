package org.springframework.social.intel.api;

import org.springframework.social.intel.api.impl.IntelTemplate;
import org.springframework.social.intel.context.api.ContextOperations;
import org.springframework.social.intel.curation.api.CurationOperation;
import org.springframework.social.intel.feedback.api.FeedbackOperation;

/**
 * Copyright 2012 Current Worldwide LLC.
 * 
 * Interface specifying a basic set of operations for interacting with Intel.
 * Implemented by {@link IntelTemplate}.
 * 
 * @author Michael Lavelle
 */
public interface Intel {
	public ContextOperations contextOperations();
	public MeOperations meOperations();
	public FeedbackOperation feedback();
	public CurationOperation curation();
}
