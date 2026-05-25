package org.jboss.as.quickstarts.library;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class LibraryList<T extends LibraryItem> extends ArrayList<T>{
	@Override
	public boolean add(T t) {
		// If the provided item is already in the list it can't be added again
		for (LibraryItem item : this) {
			if (t.equals(item)) {
				return false;
			}
		}
		return super.add(t);
	}
}