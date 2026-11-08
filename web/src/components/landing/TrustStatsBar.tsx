import React from 'react';
import { 
  Users, 
  BookOpen, 
  Calendar, 
  TrendingUp, 
  ShieldCheck 
} from 'lucide-react';

export const TrustStatsBar: React.FC = () => {
  const stats = [
    {
      icon: Users,
      title: "Workplace Focused",
      subtitle: "English Training"
    },
    {
      icon: BookOpen,
      title: "4 Structured",
      subtitle: "Modules"
    },
    {
      icon: Calendar,
      title: "16-24 Weeks",
      subtitle: "(Practice + Application)"
    },
    {
      icon: TrendingUp,
      title: "Real Progress",
      subtitle: "& Skill Improvement"
    },
    {
      icon: ShieldCheck,
      title: "For Professionals,",
      subtitle: "Students & Job Seekers"
    }
  ];

  return (
    <section className="relative z-20 -mt-6 sm:-mt-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div className="bg-[#0E172B] border border-slate-700/80 rounded-3xl p-6 sm:p-8 shadow-2xl shadow-black/50">
        <div className="grid grid-cols-2 md:grid-cols-5 gap-6 divide-y md:divide-y-0 md:divide-x divide-slate-800">
          {stats.map((stat, idx) => {
            const Icon = stat.icon;
            return (
              <div 
                key={idx} 
                className={`flex items-center gap-3.5 ${idx !== 0 ? 'pt-4 md:pt-0 md:pl-6' : ''}`}
              >
                <div className="w-11 h-11 rounded-2xl bg-amber-500/10 border border-amber-500/30 flex items-center justify-center text-amber-400 shrink-0">
                  <Icon className="w-5 h-5" />
                </div>
                <div>
                  <div className="text-sm font-bold text-white leading-tight">
                    {stat.title}
                  </div>
                  <div className="text-xs text-slate-400 font-medium">
                    {stat.subtitle}
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

