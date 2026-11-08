import React, { useState } from 'react';
import { 
  Users, 
  MessageSquare, 
  Briefcase, 
  FileText, 
  CheckCircle, 
  ArrowRight, 
  Play, 
  BookOpen, 
  Sparkles,
  Layers,
  ChevronRight
} from 'lucide-react';

interface CourseDetailsSectionProps {
  onStartCourse: () => void;
}

export const CourseDetailsSection: React.FC<CourseDetailsSectionProps> = ({ onStartCourse }) => {
  const [selectedModule, setSelectedModule] = useState(1);

  const modules = [
    {
      id: 1,
      badge: "Module 1 • Weeks 1 – 4",
      title: "Building Confidence & Core Workplace English",
      desc: "Lay strong foundations in executive greetings, self-introductions, polite requests, and active listening.",
      icon: Users,
      weeks: 4,
      status: "Ready to Explore",
      highlights: [
        "Day 1: Professional Introductions & First Impressions",
        "Day 2: Clarifying Questions & Confirming Understanding",
        "Day 3: Expressing Opinions with Assertive Civility",
        "Day 4: Polite Refusals & Offering Practical Alternatives",
        "Day 5: Week 1 Synthesis & Live AI Role-Play Review"
      ]
    },
    {
      id: 2,
      badge: "Module 2 • Weeks 5 – 8",
      title: "Workplace Conversation & Team Communication",
      desc: "Master stand-up updates, cross-functional alignment, casual office small talk, and constructive feedback.",
      icon: MessageSquare,
      weeks: 4,
      status: "Upcoming",
      highlights: [
        "Running efficient daily standups without rambling",
        "Disagreeing constructively in team syncs",
        "Cross-functional collaboration with tech & non-tech peers",
        "Giving and receiving peer performance feedback"
      ]
    },
    {
      id: 3,
      badge: "Module 3 • Weeks 9 – 12",
      title: "Managing People, Meetings & Performance",
      desc: "Lead high-stakes meetings, run 1-on-1 appraisals, resolve interpersonal friction, and set managerial expectations.",
      icon: Briefcase,
      weeks: 4,
      status: "Upcoming",
      highlights: [
        "Opening, facilitating, and summarizing leadership meetings",
        "Conducting impactful quarterly appraisal discussions",
        "De-escalating workplace conflicts with diplomatic tact",
        "Delegating tasks clearly with measurable accountability"
      ]
    },
    {
      id: 4,
      badge: "Module 4 • Weeks 13 – 16",
      title: "Emails, Reports & High-Impact Presentations",
      desc: "Craft executive emails that get immediate responses, write concise summaries, and pitch proposals persuasively.",
      icon: FileText,
      weeks: 4,
      status: "Upcoming",
      highlights: [
        "The BLUF Method (Bottom Line Up Front) for executive emails",
        "Synthesizing complex project metrics into 1-page briefs",
        "Delivering confident slide presentations to senior stakeholders",
        "Handling tough Q&A sessions under pressure"
      ]
    }
  ];

  const currentModule = modules.find(m => m.id === selectedModule) || modules[0];

  return (
    <section id="courses" className="py-20 md:py-32 relative bg-[#0B1220] border-t border-b border-slate-800">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {/* Section Heading */}
        <div className="text-center max-w-3xl mx-auto mb-16">
          <div className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-amber-500/10 border border-amber-500/25 text-amber-300 text-xs font-semibold uppercase tracking-wider mb-4">
            <Layers className="w-3.5 h-3.5" />
            <span>Comprehensive Curriculum</span>
          </div>
          <h2 className="text-3xl sm:text-4xl lg:text-5xl font-extrabold text-white font-display">
            Our <span className="text-amber-400">Learning Journey</span>
          </h2>
          <p className="mt-4 text-base sm:text-lg text-slate-300">
            A 4-Module managerial program built to develop complete corporate communication fluency.
          </p>
        </div>

        {/* 4-Step Interactive Timeline Tabs */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 mb-12">
          {modules.map((m) => {
            const Icon = m.icon;
            const isSelected = selectedModule === m.id;
            return (
              <button
                key={m.id}
                onClick={() => setSelectedModule(m.id)}
                className={`text-left p-5 rounded-2xl border transition-all relative overflow-hidden flex flex-col justify-between ${
                  isSelected 
                    ? 'bg-[#152238] border-amber-500 shadow-xl shadow-amber-500/10' 
                    : 'bg-[#0E172B]/70 border-slate-800 hover:border-slate-700 hover:bg-[#0E172B]'
                }`}
              >
                {isSelected && (
                  <div className="absolute top-0 left-0 right-0 h-1 bg-gradient-to-r from-amber-400 to-yellow-300"></div>
                )}
                <div>
                  <div className="flex items-center justify-between mb-3">
                    <span className={`w-8 h-8 rounded-xl flex items-center justify-center font-bold text-sm ${
                      isSelected 
                        ? 'bg-amber-400 text-slate-950 shadow-md shadow-amber-500/30' 
                        : 'bg-slate-800 text-slate-400'
                    }`}>
                      {m.id}
                    </span>
                    <span className="text-[10px] font-semibold text-slate-400">
                      Weeks {m.id === 1 ? '1–4' : m.id === 2 ? '5–8' : m.id === 3 ? '9–12' : '13–16'}
                    </span>
                  </div>
                  <h3 className={`text-sm font-bold leading-snug line-clamp-2 ${isSelected ? 'text-white' : 'text-slate-300'}`}>
                    {m.title}
                  </h3>
                </div>

                <div className="mt-4 pt-3 border-t border-slate-800/80 flex items-center justify-between text-[11px]">
                  <span className={isSelected ? 'text-amber-400 font-bold' : 'text-slate-500'}>
                    {m.id === 1 ? 'Active Module' : 'View Syllabus'}
                  </span>
                  <ChevronRight className={`w-3.5 h-3.5 ${isSelected ? 'text-amber-400 translate-x-1' : 'text-slate-600'} transition-transform`} />
                </div>
              </button>
            );
          })}
        </div>

        {/* Selected Module Deep Dive Showcase */}
        <div className="bg-[#101A2E] border border-slate-700/80 rounded-3xl p-6 sm:p-10 shadow-2xl relative overflow-hidden">
          <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-center">
            
            {/* Left Column: Module Details */}
            <div className="lg:col-span-7 space-y-6">
              <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-amber-500/15 border border-amber-500/30 text-amber-300 text-xs font-bold">
                <Sparkles className="w-3.5 h-3.5 text-amber-400" />
                <span>{currentModule.badge}</span>
              </div>

              <h3 className="text-2xl sm:text-3xl font-extrabold text-white">
                {currentModule.title}
              </h3>

              <p className="text-slate-300 text-sm sm:text-base leading-relaxed">
                {currentModule.desc}
              </p>

              {/* Module Highlights List */}
              <div className="space-y-3 pt-2">
                <div className="text-xs font-bold text-amber-400 uppercase tracking-wider">
                  Key Skills & Daily Breakdown:
                </div>
                {currentModule.highlights.map((point, i) => (
                  <div key={i} className="flex items-start gap-3 text-slate-300 text-sm">
                    <CheckCircle className="w-4 h-4 text-emerald-400 shrink-0 mt-0.5" />
                    <span>{point}</span>
                  </div>
                ))}
              </div>

              {/* Action Button */}
              <div className="pt-4 flex flex-wrap gap-4 items-center">
                <button
                  onClick={onStartCourse}
                  className="px-6 py-3.5 text-sm font-bold text-slate-950 bg-gradient-to-r from-amber-400 to-amber-500 hover:from-amber-300 hover:to-amber-400 rounded-xl shadow-lg shadow-amber-500/25 flex items-center gap-2 transition-all"
                >
                  <Play className="w-4 h-4 fill-current" />
                  <span>Start Module 1 Day 1 Now</span>
                </button>
                <span className="text-xs text-slate-400">
                  Includes 7-step pedagogical method & AI Role-Play
                </span>
              </div>
            </div>

            {/* Right Column: Visual Infographic of Career Escalation */}
            <div className="lg:col-span-5 relative">
              <div className="rounded-2xl overflow-hidden border border-slate-700/80 shadow-2xl relative group">
                <img 
                  src="/assets/career-ladder-trophy.jpg" 
                  alt="Career Progression with Elevora" 
                  className="w-full h-auto object-cover group-hover:scale-105 transition-transform duration-700"
                />
                <div className="absolute inset-0 bg-gradient-to-t from-[#101A2E] via-transparent to-transparent opacity-80"></div>
                <div className="absolute bottom-4 left-4 right-4 bg-slate-950/80 backdrop-blur-md p-3.5 rounded-xl border border-amber-500/30">
                  <div className="text-xs font-bold text-white flex items-center justify-between">
                    <span>Outcome: Executive Communication</span>
                    <span className="text-amber-400">4 Modules</span>
                  </div>
                  <div className="text-[11px] text-slate-400 mt-1">
                    Transition from nervous hesitations to structured, impactful executive dialogue.
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>

      </div>
    </section>
  );
};
