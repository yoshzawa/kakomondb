package kakomon3.jdo.jdostatic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import kakomon3.jdo.Member;

public class MemberStatic extends StaticCommon {
	final public static List<Member> getList(PersistenceManager pm) {

		return getList(pm, Member.class);
	}

	final public static Map<String, Member> getMap(PersistenceManager pm) {
		List<Member> list = Member.getList(pm);
		return getMap(pm, list);
	}

	final public static Map<String, Member> getMap(PersistenceManager pm,
			List<Member> memberList) {

		Map<String, Member> mapMember = new HashMap<String, Member>();

		for (Member m : memberList) {
			mapMember.put(m.getMail(), m);
		}
		return mapMember;
	}

	final public static Member getById(PersistenceManager pm, String id) {
		try {
			Member kaiin = (Member) pm.getObjectById(Member.class, id);
			return kaiin;
		} catch (JDOObjectNotFoundException e) {
			return null;
		}
	}
}
