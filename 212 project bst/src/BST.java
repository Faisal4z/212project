import java.util.ArrayList;

public class BST<T> {
	BSTNode<T> root, current;

	public BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve() {
		return current.data;
	}

	public boolean findkey(String tkey) {
		BSTNode<T> p = root, q = root;

		if (empty())
			return false;

		while (p != null) {
			q = p;
			if (p.key.compareTo(tkey) == 0) {
				current = p;
				return true;
			} else if (tkey.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		current = q;
		return false;
	}

	public boolean insert(String k, T val) {
		BSTNode<T> p, q = current;

		if (findkey(k)) {
			current = q;
			return false;
		}
		p = new BSTNode<T>(k, val);

		if (empty()) {
			root = current = p;
			return true;
		} else {
			if (k.compareTo(current.key) < 0)
				current.left = p;
			else
				current.right = p;
		}
		current = p;
		return true;
	}

	private BSTNode<T> remove_aux(String key, BSTNode<T> p, BooleanWrapper flag) {
		BSTNode<T> q, child = null;
		if (p == null)
			return null;
		if (key.compareTo(p.key) < 0)
			p.left = remove_aux(key, p.left, flag); // go left
		else if (key.compareTo(p.key) > 0)
			p.right = remove_aux(key, p.right, flag); // go right
		else { // key is found
			flag.set(true);
			if (p.left != null && p.right != null) { // two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			} else {
				if (p.right == null) // one child
					child = p.left;
				else if (p.left == null) // one child
					child = p.right;
				return child;
			}
		}
		return p;
	}

	public boolean remove_key(String tkey) {
		BooleanWrapper removed = new BooleanWrapper(false);

		BSTNode<T> p;
		p = remove_aux(tkey, root, removed);
		current = root = p;
		return removed.getValue();
	}

	private BSTNode<T> find_min(BSTNode<T> p) {
		if (p == null)
			return null;

		while (p.left != null) {
			p = p.left;
		}

		return p;
	}

	public void inOrderTraversal(BSTNode<T> root) {
		if (root != null) {
			// Traverse the left subtree
			inOrderTraversal(root.left);
			// Print the current node's value
			if (root.data instanceof Contact)
				((Contact) root.data).Display();
			else
				((Event) root.data).Display();
			// Traverse the right subtree
			inOrderTraversal(root.right);
		}
	}

	public boolean searchForContact(int v, String val, BSTNode<T> broot, BooleanWrapper flag) {
		// first arg it is for slect type of search
		// this method well return true if it is Exist and move the current to this
		// item. else it well retrun false
		if (broot != null && !flag.getValue()) {
			if (empty())// 1
				return false;// 1

			searchForContact(v, val, broot.left, flag);
			switch (v) {

			case 2: {// n
				if (((Contact) broot.data).getPhone().equalsIgnoreCase(val)) {
					current = broot;
					flag.set(true);
					return true;// n
				}
				break;// n

			}
			case 3: {// n
				if (((Contact) broot.data).getEmail().equalsIgnoreCase(val)) {
					current = broot;
					flag.set(true);
					return true;// n
				}
				break;// n
			}
			case 4: {// n
				if (((Contact) broot.data).getAddress().equalsIgnoreCase(val)) {
					current = broot;
					flag.set(true);
					return true;// n
				}
				break;// n
			}
			case 5: {// n
				if (((Contact) broot.data).getBirthday().equalsIgnoreCase(val)) {
					current = broot;
					flag.set(true);
					return true;// n
				}
				break;// n

			}
			}
			searchForContact(v, val, broot.right, flag);

		}
		return false;// does not find it//1
		// bigO(n^2)
	}

	public void PrintEventMatchesContact(String contactName, BSTNode<T> broot, BooleanWrapper flag) {
		if (broot != null) {
			PrintEventMatchesContact(contactName, broot.left, flag);

			if (!((Event) broot.data).getInvolvedContacts().empty()) {// n
				if (((Event) broot.data).getInvolvedContacts().findkey(contactName)) {// n^3
					((Event) broot.data).Display();//
					flag.set(true);// n
				}
			}

			PrintEventMatchesContact(contactName, broot.right, flag);
		}
	}

	public boolean PrintByFirstName(String val, BSTNode<T> broot, BooleanWrapper flag) {
		
		if (broot != null) {
			PrintByFirstName(val, broot.left, flag);

			String spiltNames[] = ((Contact) broot.data).getName().split(" "); // n^2 //to take the first name;
			String firstName = spiltNames[0]; // n
			if (firstName.equalsIgnoreCase(val)) { // n
				((Contact) broot.data).Display(); // n
				flag.set(true);
			}

			PrintByFirstName(val, broot.right, flag);

		}
		return flag.getValue();
	}

	public void PrintAllNamesOfcontact(IntWrapper count, BSTNode<T> broot) {
		if (broot != null) {
			PrintAllNamesOfcontact(count, broot.left);
			count.setValue(count.getValue() + 1);
			System.out.println((count.getValue()) + "-" + ((Contact) broot.data).getName());

			PrintAllNamesOfcontact(count, broot.right);
		}
	}

	public void inOrderDeletingcountacts(String contactName, BSTNode<T> broot) {
		try {
			if (broot != null) {

				inOrderDeletingcountacts(contactName, broot.left);

				if (((Event) broot.data).getInvolvedContacts().remove_key(contactName)) {
					if (((Event) broot.data).getInvolvedContacts().empty()) {
						remove_key(((Event) broot.data).getEventTitle());
						// d.add(((Event) broot.data).getEventTitle());
					}
				}

				inOrderDeletingcountacts(contactName, broot.right);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public void inOrderCheekingtheDate(String conName, String dateAndTime, BSTNode<T> broot, BooleanWrapper flag) {

		if (broot != null) {
			inOrderCheekingtheDate(conName, dateAndTime, broot.left, flag);

			if (((Event) broot.data).getDateAndTime().equalsIgnoreCase(dateAndTime)
					&& !((Event) broot.data).getInvolvedContacts().empty()) {

				if (((Event) broot.data).getInvolvedContacts().findkey(conName)) {
					flag.set(true);
					return;
				}
			}

			inOrderCheekingtheDate(conName, dateAndTime, broot.right, flag);
		}
	}

}
