package com.bigbass1997.coreperms.util;

public class DefaultConfig {
	
	public static final String permsConfig = "{" + "\n	\"groups\": [" + "\n		{" + "\n			\"name\": \"Regular\"," + "\n			\"showTag\": true," + "\n			\"prefix\": \"[\"," + "\n			\"suffix\": \"]\"," + "\n			\"perms\": [" + "\n				\"mod.parent.child1\"," + "\n				\"mod.parent.child2\"," + "\n				\"mod.parent.child3\"," + "\n				\"foo.bar.*\"" + "\n			]" + "\n		}," + "\n		{" + "\n			\"name\": \"Admin\"," + "\n			\"showTag\": true," + "\n			\"prefix\": \"[\"," + "\n			\"suffix\": \"]\"," + "\n			\"perms\": [" + "\n				\"mod.parent.child1\"," + "\n				\"mod.parent.child2\"," + "\n				\"mod.parent.child3\"," + "\n				\"foo.bar.*\"" + "\n			]" + "\n		}" + "\n	]," + "\n" + "\n	\"users\": [" + "\n		{" + "\n			\"uuid\": \"5719f8d6b2824a2389025a605c06daed\"," + "\n			\"groups\": [\"Regular\", \"Admin\"]," + "\n			\"perms\": [" + "\n				\"mod.parent.*\"," + "\n				\"foo.*\"" + "\n			]" + "\n		}," + "\n		{" + "\n			\"uuid\": \"069a79f444e94726a5befca90e38aaf5\"," + "\n			\"groups\": [\"Regular\"]," + "\n			\"perms\": []" + "\n		}" + "\n	]" + "\n}";
}
