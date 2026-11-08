import React from 'react';
import { 
  Mic, 
  PlayCircle, 
  Target, 
  HelpCircle, 
  TrendingUp, 
  Smartphone,
  Bot,
  Sparkles
} from 'lucide-react';

export const FeaturesGridSection: React.FC = () => {
  const features = [
    {
      icon: Mic,
      title: "AI Speaking Tutor",
      desc: "Practice real conversations 24/7 without judgement. Get instant fluency scores, pronunciation tips, and executive tone advice."
    },
    {
      icon: PlayCircle,
      title: "Bite-sized Lessons",
      desc: "Short, focused 15-minute lessons packed with practical vocabulary, audio pronunciation, and real corporate phrases."
    },
    {
      icon: Target,
      title: "Real Scenarios",
      desc: "Navigate authentic workplace situations: stand-up updates, salary negotiations, cross-departmental syncs, and client meetings."
    },
    {
      icon: HelpCircle,
      title: "Practice & Quizzes",
      desc: "Reinforce and retain learning through interactive controlled practice, gap-fills, sentence builders, and quick recall quizzes."
    },
    {
      icon: TrendingUp,
      title: "Progress Tracking",
      desc: "See your week-over-week improvement with telemetry on vocabulary mastered, role-plays completed, and confidence indices."
    },
    {
      icon: Smartphone,
      title: "Learn Anywhere",
      desc: "Seamless synchronization across web browsers and Android native app, letting you learn on your commute or at your desk."
    }
  ];

  return (
    <section id="features" className="py-20 md:py-32 relative bg-[#080C16]">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {/* Header */}
        <div className="text-center max-w-3xl mx-auto mb-16">
          <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-amber-500/10 border border-amber-500/25 text-amber-300 text-xs font-semibold uppercase tracking-wider mb-3">
            <Sparkles className="w-3.5 h-3.5 text-amber-400" />
            <span>Built For Impact</span>
          </div>
          <h2 className="text-3xl sm:text-4xl font-extrabold text-white font-display">
            Powerful <span className="text-amber-400">Learning Features</span>
          </h2>
          <p className="mt-3 text-base sm:text-lg text-slate-300">
            Everything you need to speak, practice, and improve — all in one unified platform.
          </p>
        </div>

        {/* Feature Cards Grid (6 cards matching reference) */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {features.map((feat, idx) => {
            const Icon = feat.icon;
            return (
              <div 
                key={idx}
                className="bg-[#0E172B] border border-slate-800/90 rounded-2xl p-6 hover:border-amber-500/40 hover:bg-[#121E36] transition-all hover:-translate-y-1 group"
              >
                <div className="w-12 h-12 rounded-xl bg-amber-500/10 border border-amber-500/25 flex items-center justify-center text-amber-400 mb-4 group-hover:scale-110 group-hover:bg-amber-500 group-hover:text-slate-950 transition-all">
                  <Icon className="w-6 h-6" />
                </div>
                <h3 className="text-lg font-bold text-white mb-2">
                  {feat.title}
                </h3>
                <p className="text-sm text-slate-400 leading-relaxed">
                  {feat.desc}
                </p>
              </div>
            );
          })}
        </div>

      </div>
    </section>
  );
};

