function initializeCoreMod() {
	return {
		'itemslip': {
			'target': {
				'type': 'METHOD',
				'class': 'net.minecraft.entity.item.ItemEntity',
				'methodName': 'func_70071_h_',
				'methodDesc': '()V'
			},
			'transformer': function(method) {
				var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');
				var Opcodes = Java.type('org.objectweb.asm.Opcodes');
				var instr = method.instructions;
				var i;
				var owner = "com/turtywurty/slipperymod/SlipperyMod";
				var name = "getMultiplier";
				var desc = "()F";
				var needle;
				for (i = 0; i < instr.size(); i++) {
					var n = instr.get(i);
					if (n.getOpcode() == Opcodes.INVOKEVIRTUAL) {
						var m = n;
						if (m.name.equals("getSlipperiness")) {
							needle = m.getNext();
							break;
						}
					}
				}
				if (needle != null) {
					instr.set(needle, ASMAPI.buildMethodCall(owner, name, desc, ASMAPI.MethodType.STATIC));
				}
				return method;
			}
		}
	}
}