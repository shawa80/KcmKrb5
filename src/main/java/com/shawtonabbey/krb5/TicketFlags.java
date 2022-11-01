package com.shawtonabbey.krb5;

import sun.security.krb5.internal.Krb5;

public enum TicketFlags {
	
	TKT_FLG_FORWARDABLE            (0x40000000, Krb5.TKT_OPTS_FORWARDABLE),
	TKT_FLG_PROXIABLE              (0x10000000, Krb5.TKT_OPTS_PROXIABLE),
	TKT_FLG_PROXY                   (0x08000000, Krb5.TKT_OPTS_PROXY),
	TKT_FLG_FORWARDED               (0x20000000, Krb5.TKT_OPTS_FORWARDED),
	TKT_FLG_MAY_POSTDATE            (0x04000000, Krb5.TKT_OPTS_MAY_POSTDATE),
	TKT_FLG_POSTDATED               (0x02000000, Krb5.TKT_OPTS_POSTDATED),
	TKT_FLG_INVALID                 (0x01000000, Krb5.TKT_OPTS_INVALID),
	TKT_FLG_RENEWABLE               (0x00800000, Krb5.TKT_OPTS_RENEWABLE),
	TKT_FLG_INITIAL                 (0x00400000, Krb5.TKT_OPTS_INITIAL),
	TKT_FLG_PRE_AUTH                (0x00200000, Krb5.TKT_OPTS_PRE_AUTHENT),
	TKT_FLG_HW_AUTH                 (0x00100000, Krb5.TKT_OPTS_HW_AUTHENT),
	//TKT_FLG_TRANSIT_POLICY_CHECKED  (0x00080000),
	TKT_FLG_OK_AS_DELEGATE          (0x00040000, Krb5.TKT_OPTS_DELEGATE),
	TKT_FLG_ENC_PA_REP              (0x00010000, Krb5.TKT_OPTS_ENC_PA_REP)
	//TKT_FLG_ANONYMOUS               (0x00008000)
	;
	
	private final long bitValue;
	private final int krbFlagPos;
	
	TicketFlags(long bitValue, int krbFlagPos) {
		this.bitValue = bitValue;
		this.krbFlagPos = krbFlagPos;
	}
	
	public static boolean[] flagSet(long data) {
		
		var flags = new boolean[Krb5.TKT_OPTS_MAX];
		
		for (var flag : TicketFlags.values()) {
			if ((data & flag.bitValue) == flag.bitValue) {
				flags[flag.krbFlagPos] = true;
			}
		}
		
		return flags;
	}
	
}
