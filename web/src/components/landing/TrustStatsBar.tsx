import React from 'react';

export const TrustStatsBar: React.FC = () => {
  const stats = [
    {
      icon: "👥",
      title: "Workplace Focused",
      subtitle: "English Training"
    },
    {
      icon: "🎓",
      title: "4 Structured",
      subtitle: "Modules"
    },
    {
      icon: "📅",
      title: "16 Weeks",
      subtitle: "(Practice + Application)"
    },
    {
      icon: "📊",
      title: "Real Progress",
      subtitle: "& Skill Improvement"
    },
    {
      icon: "🛡️",
      title: "For Professionals,",
      subtitle: "Students & Job Seekers"
    }
  ];

  return (
    <section className="relative z-20 max-w-6xl mx-auto px-4 -mt-4 sm:-mt-6">
      <div className="bg-white rounded-3xl p-5 sm:p-7 shadow-[0_15px_40px_-10px_rgba(0,0,0,0.15)] border border-slate-200">
        <div className="grid grid-cols-2 md:grid-cols-5 gap-4 divide-y md:divide-y-0 md:divide-x divide-slate-200">
          {stats.map((stat, idx) => (
            <div 
              key={idx} 
              className={`flex items-center gap-3 ${idx !== 0 ? 'pt-3 md:pt-0 md:pl-5' : ''}`}
            >
              <div className="w-10 h-10 rounded-2xl bg-slate-100 flex items-center justify-center text-lg shrink-0">
                {stat.icon}
              </div>
              <div>
                <div className="text-xs sm:text-sm font-black text-slate-900 leading-tight">
                  {stat.title}
                </div>
                <div className="text-[11px] text-slate-500 font-medium">
                  {stat.subtitle}
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};
